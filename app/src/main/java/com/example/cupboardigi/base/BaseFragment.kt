package com.example.cupboardigi.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.cupboardigi.BR
import com.example.cupboardigi.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<out Any>> : Fragment(),
    CoroutineScope {

    lateinit var dataBinding: T
    val viewModel: V  by lazy { initViewModel() }
    val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_main) }

    private lateinit var job: Job


    @LayoutRes
    abstract fun setLayout(): Int
    open fun onInitialization() = Unit
    private fun viewModels(clazz: KClass<V>, factoryProducer: (() -> ViewModelProvider.Factory)? = null): Lazy<V> {
        return ViewModelLazy(clazz, { viewModelStore }, factoryProducer ?: { defaultViewModelProviderFactory })
    }

    private fun initViewModel(): V {
        val v: V by viewModels(getViewModelClass(), getViewModelFactory())
        return v
    }

    private fun getViewModelFactory(): (() -> ViewModelProvider.Factory)? = null
    protected abstract fun getViewModelClass(): KClass<V>

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    abstract fun onReadyAction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        job = Job()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, setLayout(), container, false)
        dataBinding.setVariable(BR.viewModel, viewModel)
        dataBinding.executePendingBindings()
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitialization()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onReadyAction()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}