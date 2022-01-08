package com.ryusw.afreecatv.Util

import com.ryusw.afreecatv.api.ApiService
import com.ryusw.afreecatv.data.service.BaseService

/* 싱글톤 패턴을 사용하기 위한 object, 해당 인스턴트는 어디서나 같다 */
object GithubService {
    const val baseUrl = "https://api.github.com"
    /* lazy 를 통해 해당변수가 바로 메모리상에 올라오는 것을 방지한다 */
    val instance by lazy {
        BaseService().getRetrofit(baseUrl)?.create(ApiService::class.java)
    }
}