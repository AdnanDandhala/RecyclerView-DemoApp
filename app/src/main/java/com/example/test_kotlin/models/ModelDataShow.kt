package com.example.test_kotlin.models

data class ModelDataShow(
    val viewType: Int,
    var img_id_receiver: Int,
    var message_receiver: String,
    var img_id_sender: Int,
    var message_sender: String
)
