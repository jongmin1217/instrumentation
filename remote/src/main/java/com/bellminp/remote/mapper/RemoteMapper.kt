package com.bellminp.remote.mapper

interface RemoteMapper<R, D> {

    fun mapToData(from: R): D

    fun mapToRemote(from: D): R
}