package com.shanshan.eyepetizer.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.shanshan.eyepetizer.data.Item
import java.lang.reflect.ParameterizedType


abstract class BaseFragment<V : ViewDataBinding, VM : ViewModel> : Fragment() {

    lateinit var binding: V

    lateinit var viewModel: VM

    private var owner: ViewModelStoreOwner? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), container, false)

        //viewModel = ViewModelProvider.NewInstanceFactory().create(viewModel::class.java)
        initModel()
        initData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
    }

    //请求数据
    abstract fun initData()

    //定义Observer
    abstract fun initObserver()

    abstract fun getLayoutId(): Int
    /**
     * 绑定ViewModel持有者
     *
     * @return true：Activity持有；false：fragment自身持有
     */

    private fun initModel() {

        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            // 获取真实泛型
            val types = type.actualTypeArguments[1]

            val viewModelClass = types as Class<VM>

            try {
                // 绑定viewModel
                viewModel = ViewModelProvider(this).get(viewModelClass)
            } catch (e: NoSuchFieldError) {
                e.printStackTrace()
            }
        }
    }
}