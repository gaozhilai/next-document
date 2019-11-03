import Vue from 'vue'
import Router from 'vue-router'
import DocumentPanel from '@/views//document/essay/DocumentPanel'
import ProjectPanel from '@/views/project/ProjectPanel'
import ProjectList from '@/views/project/ProjectList'
import DrawingBoard from '@/views/board/DrawingBoard';
import SharingTable from '@/views/document/table/SharingTable';
import Layout from '@/views/layout/Layout';
import Vote from '@/views/vote/Vote';
import CreateVote from '@/views/vote/components/CreateVote';
import VoteDetail from '@/views/vote/components/VoteDetail';
import VoteList from '@/views/vote/components/VoteList';

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Layout',
      component: Layout
    },
    {
      path: '/sharing_table',
      name: 'SharingTable',
      component: SharingTable
    },
    {
      path: '/layout',
      component: Layout,
      children: [
        {path: 'project_panel', component: ProjectPanel},
        {path: 'document_panel', component: DocumentPanel},
        {path: 'project_list', component: ProjectList},
        {path: 'drawing_board', component: DrawingBoard},
        {
          path: 'vote',
          component: Vote,
          children: [
            {name: 'createVote', path: 'create_vote', component: CreateVote},
            {path: 'vote_list', component: VoteList},
            {path: 'vote_detail', component: VoteDetail},
          ]
        },
      ]
    }
  ]
})
