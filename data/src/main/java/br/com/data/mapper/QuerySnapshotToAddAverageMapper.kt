package br.com.data.mapper

import br.com.domain.entity.AddAverage
import com.google.firebase.firestore.QuerySnapshot

class QuerySnapshotToAddAverageMapper : Mapper<QuerySnapshot, List<AddAverage>?>() {

    override fun transform(item: QuerySnapshot?): List<AddAverage>? {
        return item?.map {
            val addAverageMap = it.toObject(AddAverage::class.java)
            AddAverage(
                a1 = addAverageMap.a1,
                a2 = addAverageMap.a2,
                af = addAverageMap.af,
                totalNote = addAverageMap.totalNote,
                discipline = addAverageMap.discipline
            )
        }
    }
}