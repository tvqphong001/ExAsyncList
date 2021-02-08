package wee.digital.sample.ui.fragment.conversation

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import wee.digital.library.extension.notNullOrEmpty
import wee.digital.library.extension.parse
import wee.digital.library.extension.readAsset

object DataProvider {

    init {
        Observable
                .fromCallable {
                    val collection = mutableListOf<List<ConversationItem>>()
                    for (i in 1..12) {
                        val fileName = "conversations$i.json"
                        val list: List<ConversationItem>? = readAsset(fileName)
                                .parse(Array<ConversationItem>::class)
                                ?.sortedBy {
                                    it.sentTime
                                }

                        if (list.notNullOrEmpty()) {
                            collection.add(list!!)
                        }
                    }
                    return@fromCallable collection
                }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<List<ConversationItem>>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onNext(t: List<List<ConversationItem>>) {
                        assetsLiveData.value = t
                    }

                    override fun onComplete() {
                    }

                })

    }
    val assetsLiveData = MutableLiveData<List<List<ConversationItem>>?>()

}