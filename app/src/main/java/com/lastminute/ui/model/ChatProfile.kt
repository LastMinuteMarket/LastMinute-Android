package com.lastminute.ui.model

import java.time.LocalDateTime

data class ChatProfile(
    val opponent: String,
    val lastMessage: String,
    val lastSendAt: LocalDateTime
)
