import Vue from 'vue'
import Router from 'vue-router'
import DocumentPanel from '@/views/DocumentPanel'
import ProjectPanel from '@/views/ProjectPanel'
import DrawingBoard from "@/views/DrawingBoard";

Vue.use(Router)

export default new Router({
  routes: [
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
