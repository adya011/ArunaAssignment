package com.nanda.anuraassignment

import com.nanda.anuraassignment.network.api.PostService
import com.nanda.anuraassignment.utils.TestUtilities.getResponseBodyFromJsonFile
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection
import okhttp3.mockwebserver.MockWebServer


class PostApiTest {
    private val mockWebServer = MockWebServer()
    private lateinit var api: PostService

    @Before
    fun setup() {
        mockWebServer.start()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PostService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get post`() = runBlocking {

        val mockBody = getResponseBodyFromJsonFile(
            filename = "http_response_post_200.json"
        )

        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(mockBody)

        mockWebServer.enqueue(mockResponse)

        val response = api.getPosts()

        val request = mockWebServer.takeRequest()

        assert(request.method == "GET")
        assert(request.path == "/posts")
    }
}