package br.com.data.di

import br.com.data.mapper.DocumentSnapshotToSelectionItemMapper
import br.com.data.mapper.DocumentSnapshotToUserMapper
import br.com.data.mapper.Mapper
import br.com.data.mapper.QuerySnapshotToSelectedItemMapper
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
    }
}