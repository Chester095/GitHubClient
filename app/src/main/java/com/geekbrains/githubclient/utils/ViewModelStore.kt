package com.geekbrains.githubclient.utils

class ViewModelStore {
    private val storage: MutableMap<String, BaseViewModel> = mutableMapOf()

    fun saveViewModel(viewModel: BaseViewModel) {
        storage[viewModel.id] = viewModel
    }

    fun getViewModel(id: String): BaseViewModel? {
        return storage[id]
    }
}

interface BaseViewModel {
    val id: String
}