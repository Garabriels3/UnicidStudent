package br.com.data.mapper

import br.com.data.model.firestore.AddAverageData
import br.com.domain.entity.AddAverage
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class QuerySnapshotToAddAverageMapper : Mapper<QuerySnapshot, List<AddAverage>?>() {

    override fun transform(item: QuerySnapshot?): List<AddAverage>? {
        return item?.mapIndexed { index: Int, averageData: QueryDocumentSnapshot? ->
            val addAverageMap = averageData?.toObject(AddAverageData::class.java)
            AddAverage(
                id = item.documents[index].id,
                a1 = addAverageMap?.a1,
                a2 = addAverageMap?.a2,
                afNote = addAverageMap?.afNote,
                totalNote = addAverageMap?.totalNote,
                discipline = addAverageMap?.discipline,
                afState = addAverageMap?.afState,
                approveState = addAverageMap?.approveState,
                reproveState = addAverageMap?.reproveState
            )
        }
    }
}