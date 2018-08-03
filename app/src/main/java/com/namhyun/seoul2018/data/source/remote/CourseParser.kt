package com.namhyun.seoul2018.data.source.remote

import com.namhyun.seoul2018.data.Course
import org.jsoup.nodes.Document

object CourseParser {
    fun parse(id: Int, document: Document): List<Course> {
        val coursesElement = document.select(".st_course")
        var courses: List<Course> = emptyList()
        coursesElement.forEachIndexed { index, element ->
            courses += Course(
                id = getUniqueId(id, index),
                placeId = id,
                imageUrl = element.selectFirst(".pic img").attr("src"),
                title = element.selectFirst(".s_tit").ownText(),
                desc = element.selectFirst(".txt").text()
            )
        }
        return courses
    }

    private fun getUniqueId(id: Int, index: Int) = id * 10 + index
}