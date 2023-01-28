package com.adrict99.bestfilms.data.network.response.media

data class ContentResponse(
    var moviesResponse: MoviesResponse,
    var tvShowsResponse: TvShowsResponse,
    var allContentResponse: AllContentResponse
)