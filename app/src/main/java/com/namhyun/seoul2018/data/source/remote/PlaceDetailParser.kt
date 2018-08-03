package com.namhyun.seoul2018.data.source.remote

import com.namhyun.seoul2018.data.PlaceDetail
import org.jsoup.nodes.Document

object PlaceDetailParser {

    fun parse(id: Int, document: Document): PlaceDetail {
        val courseElement = document.selectFirst(".course")
        with(courseElement) {
            return PlaceDetail(
                id = id,
                imageUrl = selectFirst(".main_pic img").attr("src"),
                title = selectFirst(".tit").text(),
                subTitle = selectFirst(".sub_tit").text(),
                hash = selectFirst(".st_hash").text().trim(),
                desc = selectFirst(".txt_box").text()
                    .replace("<br>", "\n"),
                address = selectFirst(".info_tit_address").text().trim(),
                findBusWay = selectFirst(".bus_way")?.text()?.trim(),
                findMetroWay = selectFirst(".metro_way")?.text()?.trim(),
                workTime = selectFirst(".info_tit_time")?.text(),
                price = selectFirst(".info_tit_fee")?.text(),
                homeUrl = selectFirst(".info_tit_home")?.text()?.trim())
        }
    }
}