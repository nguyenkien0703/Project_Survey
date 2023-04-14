<template>
    <div class="UserSurvey">
        <div class="ContentListSurvey">
            <h2>
                Danh Sách Khỏa Sát
            </h2>
        </div>
        <div class="container mt-5">
            <form @submit.prevent="getAllSurveys()">
                <div class="row ">
                    <div class="col-3 "></div>
                    <div class="col-3 ">
                        <div class="mb-4">
                            <input type="text" class="form-control" v-model="searchName" aria-describedby="helpId"
                                placeholder="Tên khảo sát ">
                        </div>
                    </div>
                    <div class="col-3">
                        <button type="submit" class="btn btn-success">Tìm kiếm</button>
                    </div>
                    <div class="col-3 "></div>
                </div>
                <table class="table view-table mt-4">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Tên</th>
                            <th scope="col">Nội Dung</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody class="tbody">
                        <tr v-for="(survey, index) in surveys" :key="survey.id">
                            <td class="text-right">{{ (currentPage - 1) * pageSize + index + 1 }}</td>
                            <td class="text-left">{{ survey.nameSurvey }}</td>
                            <td class="text-left">{{ survey.contentSurvey }}</td>
                            <td> <router-link class="btn" :to="{ name: 'answer-survey', params: { id: survey.id } }"><i
                                        class="btn-info fa-solid fa-circle-info fa-2x"></i></router-link></td>
                        </tr>
                    </tbody>
                </table>
                <b-pagination v-model="currentPage" align="center" :total-rows="totalSurveys" :per-page="pageSize"
                    @input="getAllSurveys"></b-pagination>
            </form>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name: 'UserSurvey',
    data() {
        return {
            surveys: [],
            currentPage: 1,
            pageSize: 5,
            totalSurveys: 0,
            searchName: '',
        };
    }, mounted() {
        this.getAllSurveys()
    },
    methods: {
        getAllSurveys(pageNumber = 1) {
            axios.get(`http://localhost:8081/survey-user/surveys`, {
                params: {
                    name: this.searchName.trim(),
                    pageNumber: pageNumber - 1,
                    pageSize: this.pageSize,
                },
                headers: {
                    'Authorization': 'Bearer ' + sessionStorage.getItem('token')
                }
            }).then(response => {
                this.surveys = response.data.content;
                this.totalSurveys = response.data.totalElements;
            }).catch(error => {
                console.log(error);
            });
        }
    },
}
</script>
<style>
.text-left {
    text-align: left;
}
</style>