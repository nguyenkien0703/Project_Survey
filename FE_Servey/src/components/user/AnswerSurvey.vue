<template>
    <div class="CreateQuestionContent">
        <form method="POST" @submit="submitSurvey">
            <div class="container bg ">
                <div class="title text-center">
                    <h3 class="t1">
                        {{ survey.nameSurvey }}
                    </h3>
                    <div class="mb-5">
                        <label for="" class="form-label">Nội dung : {{ survey.contentSurvey }}</label>
                    </div>
                </div>
                <div class="title mt-5" v-for="(question, index) in survey.questionSurveys" :key="question.id">
                    <h5 class="t1">
                        Câu {{ index+=1 }} : {{ question.nameQuestion }}
                    </h5>
                    <div v-if="question.questionType.id === 1">
                        <div class="mb-5 mt-5">
                            <input type="text" class="form-control" name="" id="" aria-describedby="helpId" placeholder=""
                                v-model="textInput[question.id]">
                        </div>
                    </div>
                    <div v-else-if="question.questionType.id === 2">
                        <div v-for="option in question.optionQuestions" :key="option.id">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" :name="question.id" :id="option.nameOption"
                                    v-model="optionInput[option.id]" :value="option.id">
                                <label class="form-check-label" :for="option.id">
                                    {{ option.nameOption }}
                                </label>
                            </div>
                        </div>
                    </div>
                    <div v-else>
                        <div v-for="option in question.optionQuestions" :key="option.id">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" :name="option.id" :id="option.nameOption"
                                    v-model="optionInputChechbox[option.id]" :value="option.id">
                                <label class="form-check-label" for="inlineRadio"> {{ option.nameOption }}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="d-flex justify-content-center align-items-center mt-5">
                    <button type="submit" class="btn btn-success">Gửi</button>
                </div>
            </div>
        </form>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name: 'PreviewSurvey',
    data() {
        return {
            survey: {},
            answers: [],
            options: [],
            textInput: {},
            optionInput: {},
            optionInputChechbox: {}
        }
    }, mounted() {
        axios.get(`http://localhost:8081/survey-user/${this.$route.params.id}`, {
            headers: {
                'Authorization': 'Bearer ' + sessionStorage.getItem('token')
            }
        })
            .then(response => {
                this.survey = response.data;
                console.log(this.survey);
            })
            .catch(error => {
                console.log(error);
            });
    }, methods: {
        submitSurvey() {
            const answerSurveys = [];
            const user = JSON.parse(sessionStorage.getItem('user'));
            const idUser = user.id;
            console.log(idUser);
            for (let question of this.survey.questionSurveys) {
                if (question.questionType.id === 1) {
                    const answer = {
                        contentAnswerSurvey: this.textInput[question.id],
                        userId: idUser,
                        surveyId: this.survey.id,
                        question_Survey: question.id,
                        isDelete: false,
                    };
                    answerSurveys.push(answer);
                } else if (question.questionType.id === 2) {
                    for (let optionRadio of question.optionQuestions) {
                        if (this.optionInput[optionRadio.id]) {
                            const option = {
                                userId: idUser,
                                surveyId: this.survey.id,
                                question_Survey: question.id,
                                isDelete: false,
                                optionQuestionSurvey: optionRadio.id,
                            };
                            answerSurveys.push(option);
                        }
                    }
                } else {
                    for (let option of question.optionQuestions) {
                        if (this.optionInputChechbox[option.id]) {
                            const optionAnswer = {
                                userId: idUser,
                                surveyId: this.survey.id,
                                question_Survey: question.id,
                                isDelete: false,
                                optionQuestionSurvey: option.id,
                            };
                            answerSurveys.push(optionAnswer);
                        }
                    }
                }
            }
            const requests = [];
            if (answerSurveys.length > 0) {
                const answerSurveysRequest = axios.post('http://localhost:8081/answer-survey/answer-surveys', answerSurveys, {
                    headers: {
                        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
                    }
                });
                requests.push(answerSurveysRequest);
            }
            Promise.all(requests)
                .then({})
                .catch({});
            this.$router.push({ name: 'answer-success' });
        },
    },
}
</script>

<style>
.transparent {
    background-color: transparent !important;
}
</style>