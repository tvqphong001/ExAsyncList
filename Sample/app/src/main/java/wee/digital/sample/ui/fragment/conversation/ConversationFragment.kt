package wee.digital.sample.ui.fragment.conversation

import kotlinx.android.synthetic.main.conversation.*
import wee.digital.sample.R
import wee.digital.sample.ui.base.BaseFragment
import wee.digital.sample.ui.base.viewModel

/**
 * -------------------------------------------------------------------------------------------------
 * @Project: Sample
 * @Created: Huy 2021/02/08
 * @Organize: Wee Digital
 * @Description: ...
 * All Right Reserved
 * -------------------------------------------------------------------------------------------------
 */
class ConversationFragment : BaseFragment() {

    private val vm by lazy { viewModel(ConversationVM::class) }

    private val adapter by lazy { ConversationAdapter() }

    private var isPlaying: Boolean = false

    override fun layoutResource(): Int {
        return R.layout.conversation
    }


    override fun onViewCreated() {
        adapter.bind(conRecyclerView)
    }


    override fun onLiveDataObserve() {

        vm.conversationLiveData.observe {
            adapter.set(it)
        }
        DataProvider.assetsLiveData.observe {
            vm.playSample()
        }
    }

}