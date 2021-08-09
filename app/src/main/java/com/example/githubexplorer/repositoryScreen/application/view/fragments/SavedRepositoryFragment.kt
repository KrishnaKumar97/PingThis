import androidx.fragment.app.Fragment

//@file:Suppress("DEPRECATION")
//
//package com.nineleaps.smarttvadmin.fragments.mainActivityFragments
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.content.Intent
//import android.graphics.Color
//import android.os.Bundle
//import android.os.Vibrator
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import androidx.recyclerview.widget.RecyclerView
//import com.getbase.floatingactionbutton.FloatingActionButton
//import com.getbase.floatingactionbutton.FloatingActionsMenu
//import com.google.android.material.dialog.MaterialAlertDialogBuilder
//import com.google.android.material.textfield.TextInputEditText
//import com.nineleaps.smarttvadmin.R
//import com.nineleaps.smarttvadmin.activities.MasterImageActivity
//import com.nineleaps.smarttvadmin.adapter.DevicesGroupAdapter
//import com.nineleaps.smarttvadmin.model.GroupDataModel
//import com.nineleaps.smarttvadmin.utilities.SecuredPreferenceManager
//import com.nineleaps.smarttvadmin.viewmodel.DeviceGroupsViewModel
//import kotlinx.android.synthetic.main.fragment_device_groups.*
//
//
///**
// * Device Groups Fragment
// */
class SavedRepositoryFragment : Fragment() {
//
//    //Variables
//    private var groupDataList = ArrayList<GroupDataModel>()
//    private var selectedGroupTVList = ArrayList<String>()
//    private var selectedGroupList = ArrayList<String>()
//    private lateinit var deviceGroupViewModel: DeviceGroupsViewModel
//    private lateinit var devicesGroupAdapter: DevicesGroupAdapter
//    private lateinit var vibe: Vibrator
//
//    //Views
//    private lateinit var groupRecyclerView: RecyclerView
//    private lateinit var progressBar: ProgressBar
//    private lateinit var emptyStateImageView: ImageView
//    private lateinit var floatingActionsMenuForGroup: FloatingActionsMenu
//    private lateinit var imageFloatingActionButtonForGroup: FloatingActionButton
//    private lateinit var messageFloatingActionButtonForGroup: FloatingActionButton
//    private lateinit var deleteFloatingActionButtonForGroup: FloatingActionButton
//
//    /**
//     * Inflating fragment layout
//     */
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_device_groups, container, false)
//    }
//
//    /**
//     * Initializing fragment elements
//     */
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        initViews()
//        setAdapter()
//        initViewModel()
//        observeLiveData()
//        setVisibilities()
//        setFloatingActionButtonsListeners(selectedGroupTVList)
//    }
//
//    /**
//     * initialize Views
//     */
//    private fun initViews() {
//        groupRecyclerView = activity!!.findViewById(R.id.group_recycler_view)
//        progressBar = activity!!.findViewById(R.id.group_in_progress_bar)
//        emptyStateImageView = activity!!.findViewById(R.id.empty_state_image_view)
//        floatingActionsMenuForGroup = activity!!.findViewById(R.id.floating_action_menu_group)
//        imageFloatingActionButtonForGroup = activity!!.findViewById(R.id.add_images_group)
//        vibe = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//        messageFloatingActionButtonForGroup =
//            activity!!.findViewById(R.id.add_broadcast_message_group)
//        deleteFloatingActionButtonForGroup = activity!!.findViewById(R.id.delete_group)
//        if (SecuredPreferenceManager.getDesignation() == "Admin")
//            deleteFloatingActionButtonForGroup.visibility = View.VISIBLE
//        else
//            deleteFloatingActionButtonForGroup.visibility = View.GONE
//        progressBar.visibility = View.VISIBLE
//    }
//
//    /**
//     * initialize Recycler And Set Adapter
//     */
//    private fun setAdapter() {
//        devicesGroupAdapter = DevicesGroupAdapter(
//            groupDataList, object : DevicesGroupAdapter.GroupListener {
//                override fun onMenuClick(groupDataModel: GroupDataModel) {
//                    reloadFragment()
//                    showDialogForMenu(groupDataModel)
//                }
//
//                override fun onCardClick(groupList: ArrayList<GroupDataModel>) {
//                    selectedGroupList.clear()
//                    selectedGroupTVList.clear()
//                    for (item in groupList) {
//                        if (!selectedGroupList.contains(item.groupName)) {
//                            selectedGroupList.add(item.groupName.toString())
//                        }
//                        for (tv in item.groupDeviceList?.keys!!) {
//                            if (!selectedGroupTVList.contains(tv)) {
//                                selectedGroupTVList.add(tv)
//                            }
//                        }
//                    }
//                    setVisibilities()
//                }
//
//                override fun onClick(tvDetailList: String, groupName: String) {
//                    showDialogForCompleteTVList(tvDetailList, groupName)
//                }
//
//            }
//        )
//        groupRecyclerView.adapter = devicesGroupAdapter
//    }
//
//    /**
//     * @param groupDataModel GroupDataModel
//     * Show dialog on clicking menu button
//     */
//    @SuppressLint("InflateParams")
//    private fun showDialogForMenu(groupDataModel: GroupDataModel) {
//        val listOfTV = ArrayList<String>()
//        selectedGroupList.clear()
//        selectedGroupList.add(groupDataModel.groupName.toString())
//
//        val mDialog = MaterialAlertDialogBuilder(activity!!)
//        val view = layoutInflater.inflate(R.layout.dialog_group_menu, null)
//
//        mDialog.setCancelable(false)
//        mDialog.setView(view)
//        val builder = mDialog.create()
//        builder.show()
//
//        for (item in
//        groupDataModel.groupDeviceList?.entries!!) {
//            listOfTV.add(item.key)
//        }
//        Log.d("groupDeviceIdListMen", listOfTV.toString())
//        val whatToShow: TextView = view.findViewById(R.id.what_to_show_text_view)
//        val addImage: TextView = view.findViewById(R.id.add_image_text_view)
//        val broadcastMessage: TextView = view.findViewById(R.id.add_broadcast_message_text_view)
//        val cancelButton: Button = view.findViewById(R.id.cancel_btn)
//
//        whatToShow.setOnClickListener {
//            vibe.vibrate(20)
//            showDialogForWhatToShow(listOfTV)
//            builder.dismiss()
//        }
//
//        addImage.setOnClickListener {
//            vibe.vibrate(20)
//            val masterImageIntent = Intent(activity!!, MasterImageActivity::class.java)
//            masterImageIntent.putExtra("SelectedDeviceIdList", listOfTV)
//            startActivity(masterImageIntent)
//            builder.dismiss()
//        }
//
//        broadcastMessage.setOnClickListener {
//            vibe.vibrate(20)
//            showDialogForBroadCastForGroups(selectedGroupList, listOfTV)
//            builder.dismiss()
//        }
//
//        cancelButton.setOnClickListener {
//            builder.dismiss()
//        }
//    }
//
//    /**
//     * @param tvDetailList details of the TV
//     * @param groupName name of the group
//     * Opens a dialog box to show the list of TVs present in the group
//     */
//    private fun showDialogForCompleteTVList(tvDetailList: String, groupName: String) {
//        val mDialog = MaterialAlertDialogBuilder(activity!!)
//        val view = View.inflate(activity!!, R.layout.layout_for_tv_complete_list, null)
//
//        val tvText: TextView = view.findViewById(R.id.tv_complete_list_text_view)
//        val buttonOk: Button = view.findViewById(R.id.button_ok)
//        val groupNameHeading: TextView = view.findViewById(R.id.group_name_text_view)
//
//        groupNameHeading.text = groupName
//        tvText.text = tvDetailList
//
//
//        mDialog.setCancelable(false)
//        mDialog.setView(view)
//        val builder = mDialog.create()
//        builder.show()
//
//        buttonOk.setOnClickListener {
//            builder.dismiss()
//        }
//
//    }
//
//    /**
//     * @param list
//     * Method to show dialog for what to show
//     */
//    @SuppressLint("InflateParams")
//    private fun showDialogForWhatToShow(list: ArrayList<String>) {
//        val mDialog = MaterialAlertDialogBuilder(activity!!)
//
//        val view = layoutInflater.inflate(R.layout.edit_what_to_show_layout, null)
//
//        val selectImage: Switch = view.findViewById(R.id.image_switch)
//        val selectWebPage: Switch = view.findViewById(R.id.web_page_switch)
//        val selectVideo: Switch = view.findViewById(R.id.video_switch)
//        val applyToAll: CheckBox = view.findViewById(R.id.apply_all_checkbox)
//        val allText: TextView = view.findViewById(R.id.applied_all_text)
//        val allTextApply: TextView = view.findViewById(R.id.apply_all_text)
//
//        val updateButton: Button = view.findViewById(R.id.for_show_button_save)
//        val cancelButton: Button = view.findViewById(R.id.for_show_button_cancel)
//
//        applyToAll.visibility = View.GONE
//        allText.visibility = View.GONE
//        allTextApply.visibility = View.GONE
//
//        var whatToShow = ""
//
//        selectImage.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                selectVideo.isChecked = false
//                selectWebPage.isChecked = false
//            }
//        }
//
//        selectWebPage.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                selectVideo.isChecked = false
//                selectImage.isChecked = false
//            }
//        }
//
//        selectVideo.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                selectImage.isChecked = false
//                selectWebPage.isChecked = false
//            }
//        }
//
//
//
//        mDialog.setCancelable(false)
//        mDialog.setView(view)
//        val builder = mDialog.create()
//        builder.show()
//
//        updateButton.setOnClickListener {
//            when {
//                selectImage.isChecked -> {
//                    whatToShow = "images"
//                }
//                selectWebPage.isChecked -> {
//                    whatToShow = "webPage"
//                }
//                selectVideo.isChecked -> {
//                    whatToShow = "videos"
//                }
//            }
//
//            deviceGroupViewModel.modifyStatusPreferenceForGroup(list, whatToShow)
//            builder.dismiss()
//        }
//        cancelButton.setOnClickListener {
//            builder.dismiss()
//        }
//    }
//
//    /**
//     * @param groupList List of groups
//     * @param listOfTV List of TVs present in a group
//     * This function shows the dialog for broadcasting message
//     */
//    @SuppressLint("InflateParams")
//    private fun showDialogForBroadCastForGroups(
//        groupList: ArrayList<String>,
//        listOfTV: ArrayList<String>
//    ) {
//        val dialog = MaterialAlertDialogBuilder(activity!!)
//        val view = layoutInflater.inflate(R.layout.add_broadcast_message_dialog, null)
//
//        dialog.setCancelable(false)
//
//        dialog.setView(view)
//        val builder = dialog.create()
//        builder.show()
//
//        val cbApplyBroadcastMessageToAllDevice =
//            view.findViewById<CheckBox>(R.id.apply_all_checkbox)
//        val etMessage = view.findViewById<
//                TextInputEditText>(R.id.broadcast_message_edit_text)
//        val btCancel = view.findViewById<Button>(R.id.button_cancel)
//        val btUpdate = view.findViewById<Button>(R.id.button_update)
//        val clearMessage = view.findViewById<TextView>(R.id.clear_text)
//        val allText: TextView = view.findViewById(R.id.applied_all_text)
//        val applyForAllTextView: TextView = view.findViewById(R.id.apply_to_all_text_view)
//
//        cbApplyBroadcastMessageToAllDevice.visibility = View.GONE
//        allText.visibility = View.GONE
//        applyForAllTextView.visibility = View.GONE
//
//        btCancel.setOnClickListener {
//            builder.dismiss()
//        }
//
//        btUpdate.setOnClickListener {
//            if (etMessage.text!!.isNotEmpty()) {
//                if (SecuredPreferenceManager.getDesignation() == "Admin") {
//                    deviceGroupViewModel.postBroadcastMessageToGroupsByAdmin(
//                        etMessage.text.toString(), listOfTV
//                    )
//                } else {
//                    deviceGroupViewModel.postBroadcastMessageToGroupsByOthers(
//                        SecuredPreferenceManager.getUserName(),
//                        groupList,
//                        etMessage.text.toString()
//                    )
//                }
//            }
//            builder.dismiss()
//        }
//
//
//        cbApplyBroadcastMessageToAllDevice.setOnClickListener {
//            if (cbApplyBroadcastMessageToAllDevice.isChecked) {
//                allText.visibility = View.VISIBLE
//            } else {
//                allText.visibility = View.GONE
//            }
//        }
//
//        clearMessage.setOnClickListener {
//            etMessage.setText("")
//        }
//    }
//
//    /**
//     * init view model and fire-base call to get data of all the group data
//     */
//    private fun initViewModel() {
//        deviceGroupViewModel =
//            ViewModelProviders.of(activity!!).get(DeviceGroupsViewModel::class.java)
//
//        deviceGroupViewModel.callToGroupDataList()
//    }
//
//    /**
//     * Observes groupData list
//     */
//    private fun observeLiveData() {
//        deviceGroupViewModel.groupDataList.observe(this, Observer {
//            progressBar.visibility = View.GONE
//            if (!it.isNullOrEmpty()) {
//                emptyStateImageView.visibility = View.GONE
//                group_fragment_layout.setBackgroundColor(Color.parseColor("#F7F7F7"))
//                devicesGroupAdapter.updateGroupDataList(it)
//            } else {
//                emptyStateImageView.visibility = View.VISIBLE
//                group_fragment_layout.setBackgroundColor(Color.parseColor("#FFFFFF"))
//            }
//        })
//
//        deviceGroupViewModel.groupDataListError.observe(this, Observer {
//            emptyStateImageView.visibility = View.VISIBLE
//            Toast.makeText(activity!!, it.message, Toast.LENGTH_SHORT).show()
//        })
//    }
//
//    /**
//     * This function sets the visibility
//     */
//    private fun setVisibilities() {
//        if (!devicesGroupAdapter.booleanSelectedList.contains(true)) {
//            floatingActionsMenuForGroup.collapse()
//            floatingActionsMenuForGroup.visibility = View.GONE
//        } else {
//            floatingActionsMenuForGroup.visibility = View.VISIBLE
//        }
//    }
//
//    /**
//     * @param selectedDeviceIdList list of selected TVs
//     * show dialog  for action menu of a particular device
//     */
//    private fun setFloatingActionButtonsListeners(selectedDeviceIdList: ArrayList<String>) {
//        imageFloatingActionButtonForGroup.setOnClickListener {
//            Log.d("groupDeviceIdListIm", selectedDeviceIdList.toString())
//            val masterImageIntent = Intent(activity!!, MasterImageActivity::class.java)
//            masterImageIntent.putExtra("SelectedDeviceIdList", selectedDeviceIdList)
//            reloadFragment()
//            floatingActionsMenuForGroup.collapse()
//            startActivity(masterImageIntent)
//        }
//
//        messageFloatingActionButtonForGroup.setOnClickListener {
//            reloadFragment()
//            Log.d("groupDeviceIdListMe", selectedDeviceIdList.toString())
//            if (SecuredPreferenceManager.getDesignation() == "Admin") {
//                showDialogForBroadCastForGroups(arrayListOf(), selectedDeviceIdList)
//            } else {
//                showDialogForBroadCastForGroups(selectedGroupList, selectedDeviceIdList)
//            }
//            floatingActionsMenuForGroup.collapse()
//        }
//
//        deleteFloatingActionButtonForGroup.setOnClickListener {
//            deviceGroupViewModel.deleteGroups(selectedGroupList)
//            floatingActionsMenuForGroup.collapse()
//        }
//    }
//
//
//    /**
//     * method for OnDestroy
//     */
//    override fun onDestroy() {
//        super.onDestroy()
//        deviceGroupViewModel.removeMasterImageListener()
//        deviceGroupViewModel.removeListenerForDeletingGroups()
//    }
//
//    /**
//     * @param isVisibleToUser
//     * function used when switched between fragments
//     */
//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//        super.setUserVisibleHint(isVisibleToUser)
//        if (isVisibleToUser) {
//            reloadFragment()
//        }
//    }
//
//    /**
//     * Function to reload the fragment
//     */
//    fun reloadFragment() {
//        fragmentManager!!.beginTransaction().detach(this).attach(this).commit()
//
//    }
//
}
