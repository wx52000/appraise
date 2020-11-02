import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Appraise  from "../components/Appraise";
import Score  from "../components/Score";
import Home  from "../components/Home";
import Details from "../components/Details";
import AppraiseMain from "../components/AppraiseMain";
import TecAppraise from "../components/TecAppraise";
import TecScore from "../components/TecScore";
import TecDetails from "../components/TecDetails";
import Personal from "../components/Personal";
import Department from "../components/Department";
import AppraiseManage from "../components/ApprasieManage";
import emptyPage from "../components/emptyPage";
import GeneralProject from "../components/GeneralProject";
import DesignerProject from "../components/DesignerProject";
import PrincipalProject from "../components/PrincipalProject";
import CheckerProject from "../components/CheckerProject";
import HeadmanProject from "../components/HeadmanProject";
import AdminProject from "../components/AdminProject";
Vue.use(Router)


const VueRouterPush = Router.prototype.push
Router.prototype.push = function push (to) {
  return VueRouterPush.call(this, to).catch(err => err)
}

//replace
const VueRouterReplace = Router.prototype.replace
Router.prototype.replace = function replace (to) {
  return VueRouterReplace.call(this, to).catch(err => err)
}
export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      children:[
        {
          path: '',
          name: 'home',
          component: Score
        },
        {
          path: 'perScore',
          name: 'score',
          component: Score
        },
        {
          path: 'perDetails',
          name: 'details',
          component: Details
        },
        {
          path: 'tecScore',
          name: 'tecScore',
          component: TecScore
        },
        {
          path: 'tecDetails',
          name: 'tecDetails',
          component: TecDetails
        },
        {
          path: 'personal',
          name: 'personal',
          component: Personal
        },
        {
          path: 'department',
          name: 'department',
          component: Department
        },
        {
          path: 'appraiseManage',
          name: 'appraiseManage',
          component: AppraiseManage
        },
        {
          path: 'adminProject',
          name: 'adminProject',
          component: AdminProject
        },
      ]
    },
    {
      path: '/appraiseMain',
      name: 'AppraiseMain',
      component:  AppraiseMain,
      children: [
        {
          path: '',
          name: 'Main',
          component: Appraise,
      },
        {
          path: 'appraise',
          name: 'appraise',
          component: Appraise,
        },
        {
          path: 'tecAppraise',
          name: 'tecAppraise',
          component: TecAppraise,
        },
        {
          path: 'perScore',
          name: 'score',
          component: Score,
        },
        {
          path: 'tecScore',
          name: 'tecScore',
          component: TecScore,
        },
        {
          path: 'emptyPage',
          name: 'emptyPage',
          component: emptyPage
        },
        {
          path: 'generalProject',
          name: 'generalProject',
          component: GeneralProject
        },
        {
          path: 'principalProject',
          name: 'principalProject',
          component: PrincipalProject
        },
        {
          path: 'designerProject',
          name: 'designerProject',
          component: DesignerProject
        },
        {
          path: 'checkerProject',
          name: 'checkerProject',
          component: CheckerProject
        },
        {
          path: 'headmanProject',
          name: 'headmanProject',
          component: HeadmanProject
        }]
    },
  ]
})

