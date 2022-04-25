package uz.mnsh.sayyidsafo.utils

import uz.mnsh.sayyidsafo.R
import uz.mnsh.sayyidsafo.databinding.ActivityMainBinding

object UiData {
    var activityMainBinding:ActivityMainBinding? = null
    var arrayListSections = ArrayList<Sections>()

    fun addSections() {
        arrayListSections = ArrayList<Sections>()
        arrayListSections.add(
            Sections(
                "Жума мавъизалари",
                "80та дарс",
                R.drawable.wallpaper1,
                "Устоз Саййид Абдулбоситхон Сафохон ўғлининг жума мавъизаларидан баҳраманд бўлинг"
            )
        )
        arrayListSections.add(
            Sections(
                "Долзарб мавзулар",
                "80та дарс",
                R.drawable.wallpaper2,
                "Кундалик ҳаётимизда биз дуч келадиган долзарб мавзулар"
            )
        )
        arrayListSections.add(
            Sections(
                "Руҳий тарбия",
                "80та дарс",
                R.drawable.wallpaper3,
                "Қалб касалликларига оид мавзулар"
            )
        )
        arrayListSections.add(
            Sections(
                "Фарзанд тарбияси",
                "80та дарс",
                R.drawable.wallpaper4,
                "Устоз Саййид Абдулбоситхон Сафохон ўғлининг жума мавъизаларидан баҳраманд бўлинг"
            )
        )
        arrayListSections.add(
            Sections(
                "Саволим бор",
                "80та дарс",
                R.drawable.wallpaper5,
                "Устоз Саййид Абдулбоситхон Сафохон ўғлининг жума мавъизаларидан баҳраманд бўлинг"
            )
        )
        arrayListSections.add(
            Sections(
                "Охиратга тайёргарлик",
                "80та дарс",
                R.drawable.wallpaper6,
                "Устоз Саййид Абдулбоситхон Сафохон ўғлининг жума мавъизаларидан баҳраманд бўлинг"
            )
        )
        arrayListSections.add(
            Sections(
                "Аллоҳ таолога одоб",
                "80та дарс",
                R.drawable.wallpaper7,
                "Аллоҳ таоло билан боғланиш йўллари"
            )
        )
        arrayListSections.add(
            Sections(
                "Оғриҳли мавзулар",
                "80та дарс",
                R.drawable.wallpaper8,
                "Миллатимиз ичидаги оғриқли мавзулар"
            )
        )
    }
}
class Sections(
    var name: String,
    var count: String,
    var image: Int,
    var description: String
)