package com.codebros.eripple.screen.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codebros.eripple.model.sample.SamplePhoto
import com.codebros.eripple.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SampleViewModel : BaseViewModel() {

    private val _photos = MutableLiveData<List<SamplePhoto>?>()
    val photos: LiveData<List<SamplePhoto>?> = _photos
    //private val repository: SampleRepository = SampleRepository()

    fun postPhotos(): Job = viewModelScope.launch {

        /*val response = repository.getPhotos()
        if (response.isSuccessful) {
            val samplePhotos : List<SamplePhotoEntity>? = response.body()
            *//*samplePhotos?.map {
                SamplePhoto(
                    albumId = it.albumId,
                    photoId = it.photoId,
                    title = it.title,
                    url = it.url,
                    thumbnailUrl = it.thumbnailUrl
                )

            }*//*
            _photos.value = samplePhotos?.map {
                SamplePhoto(
                    uid = it.hashCode().toLong(),
                    type = CellType.PHOTO_CELL,
                    albumId = it.albumId,
                    photoId = it.photoId,
                    title = it.title,
                    url = it.url,
                    thumbnailUrl = it.thumbnailUrl
                )
            }

        } else {
            Log.wtf("postPhotos", "postPhotos Fail")
        }*/

    }

}