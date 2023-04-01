package com.example.myapp.informationlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.R
import com.example.myapp.base.BaseFragment
import com.example.myapp.databinding.FragmentInformationListBinding
import com.example.myapp.interfaces.QuotesApi
import com.example.myapp.model.Product
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author Juan Olguin
 */
@DelicateCoroutinesApi
class InformationListFragment : BaseFragment<FragmentInformationListBinding>(FragmentInformationListBinding::inflate) {

    private var listener: Listener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException("The activity must implement this fragment listener")
        }
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.title = resources.getString(R.string.app_name)
        populateView()
    }


    private fun populateView() {
        val quotesApi = Retrofit
            .Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuotesApi::class.java)
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                val result = quotesApi.getQuotes()
                showInfo(result.body()!!.products)
            }
        }


    }

    private fun showInfo(products: List<Product>) {
        binding.rvInformationList.layoutManager = LinearLayoutManager(context)
        binding.rvInformationList.adapter =
            InformationListAdapter(products) { it ->
                listener?.onSelectedItem(
                    it
                )
            }
    }



    companion object {
        fun newInstance(): InformationListFragment {
            return InformationListFragment()
        }
    }

    interface Listener {
        fun onSelectedItem(
            product: Product
        )
    }
}



