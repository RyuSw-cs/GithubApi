package com.ryusw.afreecatv.data.repository

import com.ryusw.afreecatv.Util.GithubService

/* 안좋은 코드..? 의존성을 주입 해줘야함 */
class GithubRepository {
    //레트로핏 객체 가져오기(싱글톤으로 관리)
    private val githubInstance = GithubService.instance

    //데이터 가져오기
    suspend fun getData(keyword: String, page: Int, section: Int) =
        githubInstance?.getRepoData("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", keyword, page, section)
}