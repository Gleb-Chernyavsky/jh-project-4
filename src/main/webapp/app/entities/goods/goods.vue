<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('jhProject4App.goods.home.title')" id="goods-heading">Goods</span>
            <router-link :to="{name: 'GoodsCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-goods">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('jhProject4App.goods.home.createLabel')">
                    Create a new Goods
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && goods && goods.length === 0">
            <span v-text="$t('jhProject4App.goods.home.notFound')">No goods found</span>
        </div>
        <div class="table-responsive" v-if="goods && goods.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('jhProject4App.goods.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('price')"><span v-text="$t('jhProject4App.goods.price')">Price</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'price'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('photo')"><span v-text="$t('jhProject4App.goods.photo')">Photo</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'photo'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="goods in goods"
                    :key="goods.id">
                    <td>
                        <router-link :to="{name: 'GoodsView', params: {goodsId: goods.id}}">{{goods.id}}</router-link>
                    </td>
                    <td>{{goods.name}}</td>
                    <td>{{goods.price}}</td>
                    <td>
                        <a v-if="goods.photo" v-on:click="openFile(goods.photoContentType, goods.photo)">
                            <img v-bind:src="'data:' + goods.photoContentType + ';base64,' + goods.photo" style="max-height: 30px;" alt="goods image"/>
                        </a>
                        <span v-if="goods.photo">{{goods.photoContentType}}, {{byteSize(goods.photo)}}</span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'GoodsView', params: {goodsId: goods.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'GoodsEdit', params: {goodsId: goods.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(goods)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
                <infinite-loading
                    ref="infiniteLoading"
                    v-if="totalItems > itemsPerPage"
                    :identifier="infiniteId"
                    slot="append"
                    @infinite="loadMore"
                    force-use-infinite-wrapper=".el-table__body-wrapper"
                    :distance='20'>
                </infinite-loading>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="jhProject4App.goods.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-goods-heading" v-text="$t('jhProject4App.goods.delete.question', {'id': removeId})">Are you sure you want to delete this Goods?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-goods" v-text="$t('entity.action.delete')" v-on:click="removeGoods()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./goods.component.ts">
</script>
