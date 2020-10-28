package br.com.data.mapper

import br.com.data.model.firestore.UserData
import br.com.domain.entity.SelectionItem
import com.google.firebase.firestore.QuerySnapshot

class QuerySnapshotToSelectedItemMapper : Mapper<QuerySnapshot, List<SelectionItem>?>() {

    override fun transform(item: QuerySnapshot?): List<SelectionItem>? {
        return item?.toObjects(UserData::class.java)
            ?.mapIndexed { index: Int, userData: UserData? ->
                SelectionItem(
                    id = item.documents[index].id
                )
            }
    }
}