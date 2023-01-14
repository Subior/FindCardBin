package com.rubelz.findcardbin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.rubelz.findcardbin.data.models.Card
import com.rubelz.findcardbin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    private val vm: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        addObservers()

        bind.cardNumberTextInputLayout.doOnTextChanged { text, start, before, count ->
            if (text?.length!! > 6) {
                vm.getCard(text.toString().toInt())
            }
        }

    }

    private fun addObservers() {
        vm.card.observe(this@MainActivity) { card ->
            if (card != null) {
                setValues(card)
            }
        }
    }

    private fun setValues(card: Card) {

        if (!card.scheme.isNullOrEmpty()) {
            bind.cardSchemeTextView.text = card.scheme
        }

        if (!card.type.isNullOrEmpty()) {
            bind.cardTypeTextView.text = card.type
        }

        if (!card.bank?.name.isNullOrEmpty()) {
            bind.bankTextView.text = card.bank?.name
        }

        if (!card.country?.name.isNullOrEmpty()) {
            bind.countryTextView.text = card.country?.name
        }

        //This is a special scenario where we have length but is equal to "null"
        if (card.number?.length.toString()
                .isNotEmpty() && card.number?.length.toString() != "null"
        ) {
            bind.cardLengthTextView.text = card.number?.length.toString()
        }

        when {
            card.prepaid?.equals(false) == true -> {
                bind.prepaidTextView.text = "no"
            }
            card.prepaid?.equals(false) == false -> {
                bind.prepaidTextView.text = "yes"
            }
            else -> {
                bind.prepaidTextView.text = "Unavailable"
            }
        }


    }


}