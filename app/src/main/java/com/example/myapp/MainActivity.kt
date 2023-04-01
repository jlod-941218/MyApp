package com.example.myapp

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.myapp.interfaces.QuotesApi
import com.example.myapp.base.BaseActivity
import com.example.myapp.informatiodetail.InformationDetailFragment
import com.example.myapp.informationlist.InformationListFragment
import com.example.myapp.model.Product
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@DelicateCoroutinesApi
class MainActivity : BaseActivity(), FragmentManager.OnBackStackChangedListener,
    InformationListFragment.Listener,InformationDetailFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainerId = R.id.fragment_container_layout
        supportFragmentManager.addOnBackStackChangedListener(this)
        navigateTo(InformationListFragment.newInstance(), false)

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackStackChanged() {
        supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)
    }

    override fun onSelectedItem(product: Product) {
        navigateTo(InformationDetailFragment.newInstance(product))
    }

    override fun goBack() {
        backToPrevious()
    }


}
