package br.com.data.di

import br.com.data.mapper.Mapper
import br.com.data.mapper.QuerySnapshotToSelectedItemMapper
import br.com.domain.entity.SelectionItem
import com.google.firebase.firestore.QuerySnapshot
import org.koin.core.qualifier.named
import org.koin.dsl.module

object MapperModules {

    const val querySnapshotToSelectionItemMapper =
        "QuerySnapshotToSelectionItemMapper"

    val mapperModules = module {
        single<Mapper<QuerySnapshot, List<SelectionItem>?>>(
            named(querySnapshotToSelectionItemMapper)
        ) {
            QuerySnapshotToSelectedItemMapper()
        }
    }
}