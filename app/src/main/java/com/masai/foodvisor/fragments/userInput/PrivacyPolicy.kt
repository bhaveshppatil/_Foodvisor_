package com.masai.foodvisor.fragments.userInput

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.masai.foodvisor.R
import com.masai.foodvisor.views.HomeScreen
import kotlinx.android.synthetic.main.fragment_privacy_policy.*

class PrivacyPolicy : Fragment(R.layout.fragment_privacy_policy) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = view.findViewById<WebView>(R.id.webView)

        tvPrivacy.setOnClickListener {
            webView.visibility = View.VISIBLE
            webView.webViewClient = WebViewClient()
            webView.loadUrl("https://www.foodvisor.io/en/privacy-policy/")
            webView.settings.javaScriptEnabled = true
            webView.settings.setSupportZoom(true)
        }

        tvTerms.setOnClickListener {
            webView.visibility = View.VISIBLE
            webView.webViewClient = WebViewClient()
            webView.loadUrl("https://www.foodvisor.io/en/privacy-policy/")
            webView.settings.javaScriptEnabled = true
            webView.settings.setSupportZoom(true)
        }

        btnApply.setOnClickListener {
            if (checkbox.isChecked) {
                val intent = Intent(context, HomeScreen::class.java)
                startActivity(intent)
            } else {
                checkbox.error = "Please check this box if you want to proceed"
            }
        }
    }
}