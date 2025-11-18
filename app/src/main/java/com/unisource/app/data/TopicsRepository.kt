package com.unisource.app.data

import com.unisource.app.model.Topic

object TopicsRepository {
    val topics = listOf(
        Topic(
            title = "Telecommunication | Technology, Examples, Devices, & Facts",
            content = "",
            imageUrl = "https://cdn.britannica.com/15/4615-004-75EA480A/Block-diagram-telecommunications-system.jpg",
            date = "2025-11-18"
        ),
        Topic(
            title = "Should AI Tools Be Allowed In Engineering Interviews?",
            content = "",
            imageUrl = "https://imageio.forbes.com/specials-images/imageserve/67d8966550c1f3a9df13e280//0x0.jpg?height=374&width=480&fit=bounds",
            date = "2025-11-18"
        ),
        Topic(
            title = "What can you build with Raspberry Pi Zero?",
            content = "",
            imageUrl = "https://www.raspberrypi.com/app/uploads/2025/11/Zero-2-HERO-Large.jpeg",
            date = "2025-08-21"
        ),
        Topic(
            title = "The Evolution of Optical Computing",
            content = "",
            imageUrl = "https://www.eetimes.com/wp-content/uploads/AdobeStock_1348773500.jpeg?fit=5120%2C3328",
            date = "2025-09-23"
        )
    )
}