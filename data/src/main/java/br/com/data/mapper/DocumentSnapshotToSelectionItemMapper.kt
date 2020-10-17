package br.com.data.mapper

import br.com.domain.entity.DisciplineList
import br.com.domain.entity.SelectionItem
import com.google.firebase.firestore.DocumentSnapshot

class DocumentSnapshotToSelectionItemMapper : Mapper<List<String>, List<SelectionItem>?>() {

    override fun transform(item: List<String>?): List<SelectionItem>? {
        return item?.map {
            SelectionItem(
                description = it
            )
        }
    }
}