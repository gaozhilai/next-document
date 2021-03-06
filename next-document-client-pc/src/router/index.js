import Vue from 'vue'
import Router from 'vue-router'
import {getToken} from "../util/userInfo";
const DocumentPanel = () => import('@/views//document/essay/DocumentPanel');
const ProjectPanel = () => import('@/views/project/ProjectPanel');
const ProjectList = () => import('@/views/project/ProjectList');
const DrawingBoard = () => import('@/views/board/DrawingBoard');
const SharingTable = () => import('@/views/document/table/SharingTable');
const Layout = () => import('@/views/layout/Layout');
const Vote = () => import('@/views/vote/Vote');
const CreateVote = () => import('@/views/vote/components/CreateVote');
const VoteDetail = () => import('@/views/vote/components/VoteDetail');
const VoteList = () => import('@/views/vote/components/VoteList');
const UserList = () => import('@/views/user/UserList');
const InstantMessaging = () => import('@/views/message/im/InstantMessaging');
const Personal = () => import('@/views/personal/Personal');
const Login = () => import('@/views/login/Login');

Vue.use(Router);

let router = new Router({
  routes: [
    {
      path: '/',
      name: 'Layout',
      component: Layout
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
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
        {path: 'user_list', component: UserList},
        {path: 'instant_messaging', component: InstantMessaging},
        {path: 'personal', component: Personal},
      ]
    }
  ]
});

router.beforeEach((to, from, next) => {
  let token = getToken();
  if (!token && to.path !== '/login') {
    next('/login')
    return;
  }
  next();
});

export default router;
