<template>
    <div class="wrapper">
        <PageHearder />
        <div class="d-flex align-items-center justify-content-center " style="height: 60vh;">
            <div class="bg-cl">
                <form @submit.prevent="login">
                    <div class="form-outline form-white mb-4">
                        <input type="text" id="typeEmailX" class="form-control form-control-lg" v-model="jwt.username"
                            placeholder="Nhập tài khoản" />
                    </div>
                    <div class="form-outline form-white mb-4">
                        <input type="password" id="typePasswordX" class="form-control form-control-lg"
                            v-model="jwt.password" placeholder="Nhập mật khẩu" />
                    </div>
                    <div class="d-flex align-items-center justify-content-center bg-cl">
                        <button class="btn btn-primary me-2" type="submit">Đăng nhập</button>
                        <router-link to="register-admin" class="btn btn-success">Đăng ký</router-link>
                    </div>
                </form>
            </div>
        </div>
        <PageFooter />
    </div>
</template>

<script>
import qs from 'qs';
import PageHearder from './components/layout/PageHearder.vue'
import PageFooter from './components/layout/PageFooter.vue'
import axios from 'axios';
export default {
    name: 'PageExternalUsers',
    components: {
        PageHearder, PageFooter
    },
    data() {
        return {
            jwt: {
                username: '',
                password: ''
            }
        };
    },
    methods: {
        login() {
            const newJwt = {
                username: this.jwt.username.trim(),
                password: this.jwt.password.trim(),
            };
            const data = qs.stringify(newJwt);
            const config = {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            };
            axios.get('http://localhost:8081/users/user-info', {
                params: {
                    userName: this.jwt.username.trim(),
                }
            })
                .then(response => {
                    const user = response.data;
                    sessionStorage.setItem('user', JSON.stringify(user));
                })
                .catch(error => {
                    console.log('Failed to get user data: ' + error);
                });
            axios.post('http://localhost:8081/auth/login',data, config)
                .then(response => {
                    const token = response.data.access_token;
                    sessionStorage.setItem('token', token);
                    alert('Đăng nhập thành công !!');
                    const user = JSON.parse(sessionStorage.getItem('user'));
                    const roleName = user.roleName;
                    if (roleName === 'ROLE_ADMIN') {
                        this.$router.push({ name: 'surveys-admin' });
                    } else {
                        this.$router.push({ name: 'surveys-user' });
                    }
                })
                .catch(error => {
                    alert('Đăng nhập thất bại' + error);
                });
        }
    }
}
</script>
<style>
.bg-cl {
    padding: 30px;
    background-color: #E3C2B9;
    border-radius: 20px;
}
</style>