import Vue from 'vue'
import Router from 'vue-router'
import DocumentPanel from '@/views//document/essay/DocumentPanel'
import ProjectPanel from '@/views/project/ProjectPanel'
import ProjectList from '@/views/project/ProjectList'
import DrawingBoard from "@/views/board/DrawingBoard";
import SharingTable from "@/views/document/table/SharingTable";
import Layout from "@/views/layout/Layout";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Layout',
      component: Layout
    },
    {
      path: '/drawing_board',
      name: 'DrawingBoard',
      component: DrawingBoard
    },
    {
      path: '/sharing_table',
      name: 'SharingTable',
      component: SharingTable
    },
    {
      path:'/layout',
      component:Layout,
      children:[
        {path:'project_panel',component:ProjectPanel},
        {path:'document_panel',component:DocumentPanel},
        {path:'project_list',component:ProjectList},
      ]
    }
  ]
})
