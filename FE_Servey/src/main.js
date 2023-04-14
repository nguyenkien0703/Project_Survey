import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import 'bootstrap/dist/css/bootstrap.min.css'
Vue.config.productionTip = false
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.js'
import 'bootstrap-vue/dist/bootstrap-vue.css'
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(VueRouter)
import PageListSurveyAdmin from './PageListSurveyAdmin.vue'
import PageCreateSurvey from './PageCreateSurvey.vue'
import PageListSurveyUser from './PageListSurveyUser.vue'
import PageSurveyUser from './PageSurveyUser.vue'
import PageListEmployeeSurvey from './PageListEmployeeSurvey.vue'
import PageFormCreateEmployee from './PageCreateEmployee.vue'
import PageExternalUsers from './PageLoginAdmin.vue'
import PageAnswerSuccess from './PageAnswerSuccess.vue'
import PageRegisterUser from './PageRegisterAdmin.vue'
import PageRegiterSuccess from './PageRegiterSuccess.vue'
import PageUpdateEmployee from './PageEditEmployee.vue'

const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'login',
      component: PageExternalUsers
    },
    {
      path: '/surveys-admin',
      name: 'surveys-admin',
      component: PageListSurveyAdmin
    },
    {
      path: '/create-survey',
      name: 'create-survey',
      component: PageCreateSurvey
    }, {
      path: '/surveys-user',
      name: 'surveys-user',
      component: PageListSurveyUser
    },
    {
      path: '/answer-survey/:id',
      name: 'answer-survey',
      component: PageSurveyUser
    },
    {
      path: '/list-employee',
      name: 'list-employee',
      component: PageListEmployeeSurvey
    },
    {
      path: '/update-employee/:id',
      name: 'update-employee',
      component: PageUpdateEmployee
    },
    {
      path: '/create-employee',
      name: 'create-employee',
      component: PageFormCreateEmployee
    },
    {
      path: '/answer-success',
      name: 'answer-success',
      component: PageAnswerSuccess
    },
    {
      path: '/register-admin',
      name: 'register-admin',
      component: PageRegisterUser
    },
    {
      path: '/register-success',
      name: 'register-success',
      component: PageRegiterSuccess
    },
  ]
})
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
