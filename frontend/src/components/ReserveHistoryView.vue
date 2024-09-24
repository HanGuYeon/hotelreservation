<template>

    <v-data-table
        :headers="headers"
        :items="reserveHistory"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ReserveHistoryView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "userId", value: "userId" },
                { text: "roomId", value: "roomId" },
                { text: "ammount", value: "ammount" },
                { text: "reserveDt", value: "reserveDt" },
                { text: "cancleDt", value: "cancleDt" },
                { text: "status", value: "status" },
                { text: "paymentMethod", value: "paymentMethod" },
                { text: "qty", value: "qty" },
                { text: "reqType", value: "reqType" },
            ],
            reserveHistory : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/reserveHistories'))

            temp.data._embedded.reserveHistories.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.reserveHistory = temp.data._embedded.reserveHistories;
        },
        methods: {
        }
    }
</script>

