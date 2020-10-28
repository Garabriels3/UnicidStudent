package br.com.data.di

import br.com.data.mapper.*
import br.com.domain.entity.AddAverage
import br.com.domain.entity.SelectionItem
import br.com.domain.entity.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import org.koin.core.qualifier.named
import org.koin.dsl.module

object MapperModules {

    const val querySnapshotToSelectionItemMapper =
        "QuerySnapshotToSelectionItemMapper"

    const val documentSnapshotToSelectionItemMapper =
        "DocumentSnapshotToSelectionItemMapper"

    const val documentSnapshotToUserMapper =
        "DocumentSnapshotToUserMapper"

    const val querySnapshotToAddAverageMapper =
        "QuerySnapshotToAddAverageMapper"

    val mapperModules = module {
        single<Mapper<QuerySnapshot, List<SelectionItem>?>>(
            named(querySnapshotToSelectionItemMapper)
        ) {
            QuerySnapshotToSelectedItemMapper()
        }

        single<Mapper<List<String>, List<SelectionItem>?>>(
            named(documentSnapshotToSelectionItemMapper)
        ) {
            DocumentSnapshotToSelectionItemMapper()
        }

        single<Mapper<DocumentSnapshot, User>>(
            named(documentSnapshotToUserMapper)
        ) {
            DocumentSnapshotToUserMapper()
        }

        single<Mapper<QuerySnapshot, List<AddAverage>?>>(
            named(querySnapshotToAddAverageMapper)
        ) {
            QuerySnapshotToAddAverageMapper()
        }
    }
}