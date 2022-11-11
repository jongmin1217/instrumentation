package com.bellminp.remote.mapper

import com.bellminp.data.model.DataLogin
import com.bellminp.data.model.DataSites
import com.bellminp.data.model.DataSitesList
import com.bellminp.remote.model.login.LoginResponse
import com.bellminp.remote.model.tree.SitesListResponse
import com.bellminp.remote.model.tree.SitesResponse

object RemoteSitesMapper: RemoteMapper<SitesResponse, DataSites> {
    override fun mapToData(from: SitesResponse): DataSites {
        return DataSites(
            from.code,
            from.message,
            from.list?.map {
                DataSitesList(
                    it.num,
                    it.fieldNum,
                    it.fieldName,
                    it.name,
                    it.managenum
                )
            }
        )
    }

    override fun mapToRemote(from: DataSites): SitesResponse {
        return SitesResponse(
            from.code,
            from.message,
            from.list?.map {
                SitesListResponse(
                    it.num,
                    it.fieldNum,
                    it.fieldName,
                    it.name,
                    it.managenum
                )
            }
        )
    }
}