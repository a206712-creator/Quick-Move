package com.example.a206712_chenhaojie_izwan_lab01

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

// 数据类（直接写在同一个文件里）
data class WalletData(
    val title: String,
    val amount: String
)

// ViewModel（同一个文件）
class WalletViewModel : ViewModel() {
    var selectedWallet = mutableStateOf(WalletData
        ("", ""))

    fun setWalletData(title: String, amount: String) {
        selectedWallet.value = WalletData(title, amount)
    }
}