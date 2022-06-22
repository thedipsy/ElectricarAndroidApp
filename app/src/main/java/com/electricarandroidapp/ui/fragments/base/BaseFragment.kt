package com.electricarandroidapp.ui.fragments.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.electricarandroidapp.repository.base.BaseRepository
import com.electricarandroidapp.viewmodel.ViewModelFactory

abstract class BaseFragment<VM : ViewModel, B: ViewBinding, R : BaseRepository> : Fragment() {

    protected lateinit var viewModel : VM
    protected lateinit var binding : B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = getFragmentBinding(inflater, container)

        val factory = ViewModelFactory(getFragmentRepository()) // vrakja fabrika so nasiot fragment
        viewModel = ViewModelProvider(this, factory)[getViewModel()] // kreira viewmodel so fabrikata

        return binding.root
    }

    abstract fun getViewModel() : Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) : B

    abstract fun getFragmentRepository() : R

    abstract fun setup()

    fun showMessage(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}