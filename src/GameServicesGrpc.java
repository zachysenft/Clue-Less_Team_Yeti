package generated_code;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: players.proto")
public final class GameServicesGrpc {

  private GameServicesGrpc() {}

  public static final String SERVICE_NAME = "GameServices";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated_code.Players.Suggestion,
      generated_code.Players.Confirmation> getMakeSuggestionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "makeSuggestion",
      requestType = generated_code.Players.Suggestion.class,
      responseType = generated_code.Players.Confirmation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated_code.Players.Suggestion,
      generated_code.Players.Confirmation> getMakeSuggestionMethod() {
    io.grpc.MethodDescriptor<generated_code.Players.Suggestion, generated_code.Players.Confirmation> getMakeSuggestionMethod;
    if ((getMakeSuggestionMethod = GameServicesGrpc.getMakeSuggestionMethod) == null) {
      synchronized (GameServicesGrpc.class) {
        if ((getMakeSuggestionMethod = GameServicesGrpc.getMakeSuggestionMethod) == null) {
          GameServicesGrpc.getMakeSuggestionMethod = getMakeSuggestionMethod = 
              io.grpc.MethodDescriptor.<generated_code.Players.Suggestion, generated_code.Players.Confirmation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GameServices", "makeSuggestion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated_code.Players.Suggestion.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated_code.Players.Confirmation.getDefaultInstance()))
                  .setSchemaDescriptor(new GameServicesMethodDescriptorSupplier("makeSuggestion"))
                  .build();
          }
        }
     }
     return getMakeSuggestionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated_code.Players.PlayerID,
      generated_code.Players.Suggestion> getSubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subscribe",
      requestType = generated_code.Players.PlayerID.class,
      responseType = generated_code.Players.Suggestion.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated_code.Players.PlayerID,
      generated_code.Players.Suggestion> getSubscribeMethod() {
    io.grpc.MethodDescriptor<generated_code.Players.PlayerID, generated_code.Players.Suggestion> getSubscribeMethod;
    if ((getSubscribeMethod = GameServicesGrpc.getSubscribeMethod) == null) {
      synchronized (GameServicesGrpc.class) {
        if ((getSubscribeMethod = GameServicesGrpc.getSubscribeMethod) == null) {
          GameServicesGrpc.getSubscribeMethod = getSubscribeMethod = 
              io.grpc.MethodDescriptor.<generated_code.Players.PlayerID, generated_code.Players.Suggestion>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GameServices", "subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated_code.Players.PlayerID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated_code.Players.Suggestion.getDefaultInstance()))
                  .setSchemaDescriptor(new GameServicesMethodDescriptorSupplier("subscribe"))
                  .build();
          }
        }
     }
     return getSubscribeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GameServicesStub newStub(io.grpc.Channel channel) {
    return new GameServicesStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GameServicesBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GameServicesBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GameServicesFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GameServicesFutureStub(channel);
  }

  /**
   */
  public static abstract class GameServicesImplBase implements io.grpc.BindableService {

    /**
     */
    public void makeSuggestion(generated_code.Players.Suggestion request,
        io.grpc.stub.StreamObserver<generated_code.Players.Confirmation> responseObserver) {
      asyncUnimplementedUnaryCall(getMakeSuggestionMethod(), responseObserver);
    }

    /**
     */
    public void subscribe(generated_code.Players.PlayerID request,
        io.grpc.stub.StreamObserver<generated_code.Players.Suggestion> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getMakeSuggestionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated_code.Players.Suggestion,
                generated_code.Players.Confirmation>(
                  this, METHODID_MAKE_SUGGESTION)))
          .addMethod(
            getSubscribeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated_code.Players.PlayerID,
                generated_code.Players.Suggestion>(
                  this, METHODID_SUBSCRIBE)))
          .build();
    }
  }

  /**
   */
  public static final class GameServicesStub extends io.grpc.stub.AbstractStub<GameServicesStub> {
    private GameServicesStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GameServicesStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameServicesStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GameServicesStub(channel, callOptions);
    }

    /**
     */
    public void makeSuggestion(generated_code.Players.Suggestion request,
        io.grpc.stub.StreamObserver<generated_code.Players.Confirmation> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMakeSuggestionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subscribe(generated_code.Players.PlayerID request,
        io.grpc.stub.StreamObserver<generated_code.Players.Suggestion> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GameServicesBlockingStub extends io.grpc.stub.AbstractStub<GameServicesBlockingStub> {
    private GameServicesBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GameServicesBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameServicesBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GameServicesBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated_code.Players.Confirmation makeSuggestion(generated_code.Players.Suggestion request) {
      return blockingUnaryCall(
          getChannel(), getMakeSuggestionMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated_code.Players.Suggestion subscribe(generated_code.Players.PlayerID request) {
      return blockingUnaryCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GameServicesFutureStub extends io.grpc.stub.AbstractStub<GameServicesFutureStub> {
    private GameServicesFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GameServicesFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameServicesFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GameServicesFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated_code.Players.Confirmation> makeSuggestion(
        generated_code.Players.Suggestion request) {
      return futureUnaryCall(
          getChannel().newCall(getMakeSuggestionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated_code.Players.Suggestion> subscribe(
        generated_code.Players.PlayerID request) {
      return futureUnaryCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_MAKE_SUGGESTION = 0;
  private static final int METHODID_SUBSCRIBE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GameServicesImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GameServicesImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MAKE_SUGGESTION:
          serviceImpl.makeSuggestion((generated_code.Players.Suggestion) request,
              (io.grpc.stub.StreamObserver<generated_code.Players.Confirmation>) responseObserver);
          break;
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((generated_code.Players.PlayerID) request,
              (io.grpc.stub.StreamObserver<generated_code.Players.Suggestion>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GameServicesBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GameServicesBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated_code.Players.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GameServices");
    }
  }

  private static final class GameServicesFileDescriptorSupplier
      extends GameServicesBaseDescriptorSupplier {
    GameServicesFileDescriptorSupplier() {}
  }

  private static final class GameServicesMethodDescriptorSupplier
      extends GameServicesBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GameServicesMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GameServicesGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GameServicesFileDescriptorSupplier())
              .addMethod(getMakeSuggestionMethod())
              .addMethod(getSubscribeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
