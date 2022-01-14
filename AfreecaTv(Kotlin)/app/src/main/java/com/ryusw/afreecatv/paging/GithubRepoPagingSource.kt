package com.ryusw.afreecatv.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ryusw.afreecatv.api.ApiService
import com.ryusw.afreecatv.models.RepoModel
import okio.IOException
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX = 1

/**
 * 페이징 데이터 생성 및 처리
 * apiService = 데이터를 제공하는 인스턴스
 * keyword = 키워드 쿼리값을 위한 값
 */

class GithubRepoPagingSource(private val apiService: ApiService, private val keyword: String) :
    PagingSource<Int, RepoModel>() {

    /* 스와이프로 인한 refresh, 새 데이터를 로드할때 사용됨 */
    override fun getRefreshKey(state: PagingState<Int, RepoModel>): Int? {
        return null
    }

    /* 데이터 로드 */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoModel> {
        val currentSection = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = apiService.getRepoData(
                "Your git token",
                keyword,
                10,
                currentSection
            )
            val data = response.body()?.items ?: emptyList()
            val retrieveData = mutableListOf<RepoModel>()
            retrieveData.addAll(data)
            //로드에 성공
            //data = 전송되는 데이터
            //prev = 위에, next = 아래
            LoadResult.Page(
                data = retrieveData,
                //전의 데이터
                prevKey = if (currentSection == STARTING_PAGE_INDEX) null else currentSection - 1,
                //다음 데이터
                nextKey = if (retrieveData.isEmpty()) null else currentSection + 1
            )
        } catch (e: IOException) {
            //실패
            return LoadResult.Error(e)
        }  catch (e: HttpException){
            return LoadResult.Error(e)
        }
    }
}