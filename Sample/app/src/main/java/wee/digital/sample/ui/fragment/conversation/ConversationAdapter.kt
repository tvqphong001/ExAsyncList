package wee.digital.sample.ui.fragment.conversation

import android.view.View
import kotlinx.android.synthetic.main.conversation_item.view.*
import wee.digital.library.adapter.BaseRecyclerAdapter
import wee.digital.library.extension.load
import wee.digital.sample.R

/**
 * -------------------------------------------------------------------------------------------------
 * @Project: Sample
 * @Created: Huy 2021/02/08
 * @Organize: Wee Digital
 * @Description: ...
 * All Right Reserved
 * -------------------------------------------------------------------------------------------------
 */
class ConversationAdapter : BaseRecyclerAdapter<ConversationItem>() {

    override fun layoutResource(model: ConversationItem, position: Int): Int {
        return R.layout.conversation_item
    }

    /**
     * [R.layout.conversation_item] view binding
     */
    override fun View.onBindModel(model: ConversationItem, position: Int, layout: Int) {
        conImageViewPhoto.load(model.userPhoto)
        conTextViewName.text = model.userDisplayName
        conTextViewLastMessage.text = model.message
        conTextViewTime.text = model.sentTime
    }
}