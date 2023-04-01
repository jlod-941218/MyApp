package com.example.myapp.informatiodetail

import android.content.Context
import android.os.Bundle
import android.view.*
import com.bumptech.glide.Glide
import com.example.myapp.R
import com.example.myapp.base.BaseFragment
import com.example.myapp.databinding.FragmentInformationDetailBinding
import com.example.myapp.informationlist.InformationListFragment
import com.example.myapp.model.Product

class InformationDetailFragment :
    BaseFragment<FragmentInformationDetailBinding>(FragmentInformationDetailBinding::inflate) {
    private var listener: InformationDetailFragment.Listener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InformationDetailFragment.Listener) {
            listener = context
        } else {
            throw RuntimeException("The activity must implement this fragment listener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUiElements(arguments?.getSerializable(TITLE) as Product)
        setListeners()

    }


    private fun setUiElements(product: Product) {
        activity?.title = product.title
        Glide.with(this)
            .load(product.thumbnail)
            .into(binding.ivInformationDetail)

        binding.tvDescription.text = product.description
        binding.tvDetailPrice.text = StringBuilder().apply {
            append("Precio: ")
            append(product.price.toString())
        }

    }

    private fun setListeners() {
        binding.cvGoBack.setOnClickListener {
            listener?.goBack()
        }
    }


    companion object {
        private const val TITLE = "title"

        fun newInstance(
            information: Product
        ): InformationDetailFragment {
            return InformationDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(TITLE, information)
                }
            }
        }
    }

    interface Listener {
        fun goBack()
    }


}