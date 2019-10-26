import Vue from 'vue'
import Router from 'vue-router'
import DocumentPanel from '@/views//document/essay/DocumentPanel'
import ProjectPanel from '@/views/project/ProjectPanel'
import DrawingBoard from "@/views/board/DrawingBoard";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'ProjectPanel',
      component: ProjectPanel
    },
    {
      path: '/document_panel',
      name: 'DocumentPanel',
      component: DocumentPanel
    },
    {
      path: '/project_panel',
      name: 'ProjectPanel',
      component: ProjectPanel
    },
    {
      path: '/drawing_board',
      name: 'DrawingBoard',
      component: DrawingBoard
    }
  ]
})
