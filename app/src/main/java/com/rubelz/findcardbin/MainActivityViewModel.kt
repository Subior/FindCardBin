package com.rubelz.findcardbin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rubelz.findcardbin.data.apis.BinApiService
import com.rubelz.findcardbin.data.models.Card
import com.rubelz.findcardbin.data.remote.RetrofitHelper
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _card = MutableLiveData<Card>()
    val card: LiveData<Card> = _card

    fun getCard(bin: Int) {
        viewModelScope.launch {
            val retro = RetrofitHelper.getInstance().create(BinApiService::class.java).getCard(bin)
            if (retro.isSuccessful) {
                _card.postValue(retro.body())
            }
        }
    }

}