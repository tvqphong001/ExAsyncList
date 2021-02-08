package wee.digital.sample.ui.fragment.conversation

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import wee.digital.sample.ui.base.BaseViewModel
import java.util.concurrent.TimeUnit

/**
 * -------------------------------------------------------------------------------------------------
 * @Project: Sample
 * @Created: Huy 2021/02/08
 * @Organize: Wee Digital
 * @Description: ...
 * All Right Reserved
 * -------------------------------------------------------------------------------------------------
 */
class ConversationVM : BaseViewModel() {

    val conversationLiveData = MutableLiveData<List<ConversationItem>?>()

    private var samplePlayer: Disposable? = null

    private var sampleIndex: Int = 0

    fun playSample() {
        samplePlayer?.dispose()
        samplePlayer = Observable.interval(0, 500, TimeUnit.MILLISECONDS)
                .map {
                    val list = DataProvider.assetsLiveData.value ?: throw NullPointerException()
                    if (sampleIndex == list.lastIndex) {
                        sampleIndex = -1
                    }
                    sampleIndex++
                    return@map list[sampleIndex]
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    conversationLiveData.value = it
                }
    }

    fun pauseSample() {
        samplePlayer?.dispose()
    }
}