<template>

    <v-data-table
        :headers="headers"
        :items="selectMyPage"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'SelectMyPageView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            selectMyPage : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/selectMyPages'))

            temp.data._embedded.selectMyPages.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.selectMyPage = temp.data._embedded.selectMyPages;
        },
        methods: {
        }
    }
</script>

