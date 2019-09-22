package com.example.kariyertechchallenge.Presenter.Adapter

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.core.content.ContextCompat
import com.example.kariyertechchallenge.Model.MarketingModel
import com.example.kariyertechchallenge.R
import kotlinx.android.synthetic.main.row_marketting.view.*
import kotlinx.android.synthetic.main.row_marketting_child.view.*
import kotlinx.android.synthetic.main.row_marketting.view.itemName

class MarketListAdapter(context: Context, list: List<MarketingModel>) :
    BaseExpandableListAdapter() {
    lateinit var inflater: LayoutInflater
    var ctnx = context
    var markettingList = list

    override fun getGroup(groupPosition: Int): Any {
        return markettingList[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {

        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var cnvrtView = convertView
        if (cnvrtView == null) {
            inflater = ctnx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            cnvrtView = inflater.inflate(R.layout.row_marketting, null)
        }
        else
            cnvrtView = convertView
        //Listede ki row setledik.
        var rowItem = markettingList[groupPosition]

        //Row'daki itemler yerleştirildi.
        cnvrtView!!.marketName.text = rowItem.marketName
        cnvrtView.itemName.text = rowItem.orderName
        //Float olduğundan .2f kullandık.
        cnvrtView.itemMoney.text =  String.format(ctnx.getString(R.string.itemMoney), rowItem.productPrice)
        cnvrtView.status.text = rowItem.productState
        cnvrtView.date.text = rowItem.date

        //Ay değeri setlendi düzenlendi.
        setMont(cnvrtView,rowItem.month)
        //Status durumuna göre icon ve text renklendirildi.
        setStatusIcon(cnvrtView, rowItem,ctnx)


        return cnvrtView
    }



    override fun getChildrenCount(groupPosition: Int): Int {
        return if (markettingList.get(groupPosition).productDetail != null) 1
        else 0
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return markettingList.get(groupPosition).productDetail
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var cnvrtView = convertView

        if (cnvrtView == null) {

            inflater = ctnx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            cnvrtView = inflater.inflate(R.layout.row_marketting_child, null)

        }
        var rowItem = markettingList[groupPosition].productDetail

        cnvrtView!!.itemChildName.text = rowItem.orderDetail
        cnvrtView.itemChildMoney.text = String.format(ctnx.getString(R.string.itemMoney), rowItem.summaryPrice)
        return cnvrtView!!
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return markettingList.size
    }

    //Ay değeri setlendi düzenlendi.
    private fun setMont(cnvrtView: View, month: String) {

        when(month){
            "01"-> {
                cnvrtView.mont.text = "Ocak"
            }
            "02"-> {
                cnvrtView.mont.text = "Şubat"
            }

            "03"-> {
                cnvrtView.mont.text = "Mart"
            }

            "04"-> {
                cnvrtView.mont.text = "Nisan"
            }

            "05"-> {
                cnvrtView.mont.text = "Mayıs"
            }

            "06"-> {
                cnvrtView.mont.text = "Haziran"
            }

            "07"-> {
                cnvrtView.mont.text = "Temmuz"
            }

            "08"-> {
                cnvrtView.mont.text = "Ağustos"
            }

            "09"-> {
                cnvrtView.mont.text = "Eylül"
            }

            "10"-> {
                cnvrtView.mont.text = "Ekim"
            }

            "11"-> {
                cnvrtView.mont.text = "Kasım"
            }

            "12"-> {
                cnvrtView.mont.text = "Aralık"
            }


        }
    }

    //Status durumuna göre icon ve text renklendirildi.
    private fun setStatusIcon(
        cnvrtView: View,
        rowItem: MarketingModel,
        ctnx: Context
    ) {

        when(rowItem.productState) {

            "Hazırlanıyor" -> {
                cnvrtView.status.setTextColor(ctnx.resources.getColor(R.color.colorOrange))
            }
            "Onay Bekliyor" ->{
                cnvrtView.status.setTextColor(ctnx.resources.getColor(R.color.colorRed))
                cnvrtView.statusIcon.setColorFilter(ContextCompat.getColor(ctnx, R.color.colorRed), android.graphics.PorterDuff.Mode.SRC_IN)
            }
            "Yolda" -> {
                cnvrtView.status.setTextColor(ctnx.resources.getColor(R.color.colorGreen))
                cnvrtView.statusIcon.setColorFilter(ContextCompat.getColor(ctnx, R.color.colorGreen), android.graphics.PorterDuff.Mode.SRC_IN)
            }

        }
    }
}