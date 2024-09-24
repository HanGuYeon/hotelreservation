<template>

    <v-card outlined>
        <v-card-title>
            ReserveHistory # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="UserId" v-model="item.userId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="RoomId" v-model="item.roomId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Ammount" v-model="item.ammount" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="ReserveDt" v-model="item.reserveDt" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CancleDt" v-model="item.cancleDt" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Status" v-model="item.status" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="PaymentMethod" v-model="item.paymentMethod" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Qty" v-model="item.qty" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="ReqType" v-model="item.reqType" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'ReserveHistoryViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/reserveHistories/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

