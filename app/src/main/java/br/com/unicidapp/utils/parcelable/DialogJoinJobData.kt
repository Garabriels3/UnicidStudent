package br.com.unicidapp.utils.parcelable

import android.os.Parcelable
import br.com.unicidapp.ui.component.DialogState
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
class DialogJoinJobData(
    val title: String?,
    val dialogState: DialogState?,
    val description: String?
) : Parcelable {

    @IgnoredOnParcel
    var action: (() -> Unit)? = null

    @IgnoredOnParcel
    var secondAction: (() -> Unit)? = null

    @IgnoredOnParcel
    var thirdAction: (() -> Unit)? = null

    @IgnoredOnParcel
    var label: String = EMPTY_VALUE

    @IgnoredOnParcel
    var hour: String? = EMPTY_VALUE

    @IgnoredOnParcel
    var date: String? = EMPTY_VALUE

    constructor(
        title: String?,
        dialogState: DialogState,
        description: String?,
        action: () -> Unit
    ) : this(
        title,
        dialogState,
        description
    ) {
        this.action = action
    }

    constructor(
        title: String?,
        date: String?,
        hour: String?,
        dialogState: DialogState,
        description: String?,
        action: () -> Unit
    ) : this(
        title,
        dialogState,
        description
    ) {
        this.action = action
        date.run { this@DialogJoinJobData.date = this }
        hour.run { this@DialogJoinJobData.hour = this }
    }

    constructor(
        title: String,
        dialogState: DialogState,
        description: String,
        action: () -> Unit,
        secondDialog: () -> Unit
    ) : this(
        title,
        dialogState,
        description
    ) {
        this.action = action
        this.secondAction = secondDialog
    }

    constructor(
        title: String,
        dialogState: DialogState,
        description: String,
        action: () -> Unit,
        secondAction: () -> Unit,
        thirdAction: () -> Unit,
        label: String? = null
    ) : this(
        title,
        dialogState,
        description
    ) {
        this.action = action
        this.secondAction = secondAction
        this.thirdAction = thirdAction
        label?.run { this@DialogJoinJobData.label = this }
    }

    constructor(
        title: String,
        dialogState: DialogState,
        description: String,
        action: () -> Unit,
        secondAction: () -> Unit,
        label: String? = null
    ) : this(
        title,
        dialogState,
        description
    ) {
        this.action = action
        this.secondAction = secondAction
        label?.run { this@DialogJoinJobData.label = this }
    }

    companion object {
        private const val DEFAULT_DESCRIPTION = "Sem descrição"
        private const val EMPTY_VALUE = ""
    }
}