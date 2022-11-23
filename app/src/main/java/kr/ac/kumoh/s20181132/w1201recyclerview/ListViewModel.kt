package kr.ac.kumoh.s20181132.w1201recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() { //뷰모델에서 상속받는다.
    private val songs = ArrayList<String>()
    private val list = MutableLiveData<ArrayList<String>>()//여기를 public으로 해도 된다. 하지만, 2.

    init{
        list.value = songs
    }

    fun getList(): LiveData<ArrayList<String>> = list //2. 캡슐화를 위해서 여기서 getlist를 만들어줌

    fun add(song: String){
        songs.add(song)
        list.value = songs
    }

    fun getSong(i: Int) = songs[i]
    fun getSize() = songs.size

}